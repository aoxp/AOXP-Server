package ao.model.worldobject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ao.model.worldobject.properties.ResourceSourceProperties;

public abstract class AbstractResourceSourceTest extends
		AbstractWorldObjectTest {

	@Test
	public void testResourceWorldObjctId() {
		assertEquals(((ResourceSourceProperties) objectProps).getResourceWorldObjctId(), ((AbstractResourceSource) object).getResourceWorldObjctId());
	}
}
