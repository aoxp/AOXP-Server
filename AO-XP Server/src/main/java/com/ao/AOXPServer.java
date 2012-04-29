/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.ao;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.ao.context.ApplicationContext;
import com.ao.network.ClientPacketsManager;
import com.ao.network.Connection;
import com.ao.network.DataBuffer;
import com.ao.network.ServerPacketsManager;
import com.ao.network.packet.OutgoingPacket;
import com.ao.security.SecurityManager;

/**
 * The server application.
 */
public class AOXPServer implements Runnable {

	private static final SecurityManager security = ApplicationContext.getInstance(SecurityManager.class);

	private InetSocketAddress listeningAddr;
	private int backlog;

	/**
	 * Starts running the AOXPServer. It MUST be properly initialized beforehand.
	 */
	@Override
	public void run() {
		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		/*
		 * Netty processes data by running it through a pipe that works both
		 * for incoming and outgoing data. Decoders are applied to incoming data,
		 * decoders to outgoing.
		 *
		 * The order of elements is important, since it impacts on order of execution.
		 * These scheme allows for better separation of concerns, and to add / remove
		 * steps easily and free of side-effects. For instance, we may want to add an
		 * inflate/deflate step using zlib, and that is independent from encryption
		 * and the AO protocol itself.
		 */
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();

				// Add our security layer to the pipeline
				pipeline.addLast("encrypter", new OneToOneEncoder() {

					@Override
					protected Object encode(ChannelHandlerContext ctx, Channel channel,
							Object msg) throws Exception {
						ChannelBuffer buffer = (ChannelBuffer) msg;
						security.encrypt(buffer, channel);
						return buffer;
					}
				});
				pipeline.addLast("decrypter", new OneToOneDecoder() {

					@Override
					protected Object decode(ChannelHandlerContext ctx, Channel channel,
							Object msg) throws Exception {
						ChannelBuffer buffer = (ChannelBuffer) msg;
						security.decrypt(buffer, channel);
						return buffer;
					}
				});

				/*
				 * Encoder and decoder for streamed data.
				 * Notice handling is performed as decoding happens,
				 * since we are processing a stream
				 */
				pipeline.addLast("decoder", new FrameDecoder() {

					@Override
					protected Object decode(ChannelHandlerContext ctx,
							Channel channel, ChannelBuffer buffer)
							throws Exception {

						buffer.markReaderIndex();
						boolean ret = false;
						try {
							ret = ClientPacketsManager.handle(new DataBuffer(buffer), (Connection) ctx.getAttachment());
						} catch (IndexOutOfBoundsException e) {
							// Not enough data, just ignore it
						}

						if (!ret) {
							buffer.resetReaderIndex();
							return null;
						}

						return this; // Any non-null value works...
					}

					@Override
					public void channelConnected(ChannelHandlerContext ctx,
							ChannelStateEvent e) throws Exception {
						super.channelConnected(ctx, e);
						// new user is connected, get it ready for action!
						ctx.setAttachment(new Connection(e.getChannel()));
					}
				});
				pipeline.addLast("encoder", new OneToOneEncoder() {

					@Override
					protected Object encode(ChannelHandlerContext ctx, Channel channel,
							Object msg) throws Exception {
						OutgoingPacket packet = (OutgoingPacket) msg;
						ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();

						// Wrap the channel buffer in our data buffer and write the packet into it
						DataBuffer buffer = new DataBuffer(channelBuffer);
						ServerPacketsManager.write(packet, buffer);

						return channelBuffer;
					}
				});

				return pipeline;
			}
		});

		// Bind and start to accept incoming connections.
		bootstrap.setOption("backlog", backlog);
		bootstrap.bind(listeningAddr);
	}

	/**
	 * @param listeningAddr the listeningAddr to set
	 */
	public void setListeningAddr(InetSocketAddress listeningAddr) {
		this.listeningAddr = listeningAddr;
	}

	/**
	 * @param backlog the backlog to set
	 */
	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}
}
