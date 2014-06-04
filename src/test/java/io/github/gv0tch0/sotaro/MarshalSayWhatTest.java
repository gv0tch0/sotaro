package io.github.gv0tch0.sotaro;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Test;

/** Validates in a super hacky way that {@link SayWhat}s can be JAXB2-marshalled. */ 
public class MarshalSayWhatTest {
  @Test
  public void marshal() throws Exception {
    StringWriter writer = new StringWriter();
    JAXBContext ctx = JAXBContext.newInstance(SayWhat.class);
    Marshaller marshaller = ctx.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
    SayWhat sayWhat = new SayWhat();
    sayWhat.setWhat("what");
    marshaller.marshal(new ObjectFactory().createSay(sayWhat), writer);
    String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:say xmlns:ns2=\"urn:io:github:gv0tch0:sotaro\"><what>what</what></ns2:say>";
    Assert.assertEquals(expected, writer.toString());
  }
}



