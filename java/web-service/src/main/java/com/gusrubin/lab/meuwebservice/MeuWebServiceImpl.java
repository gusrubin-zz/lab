package com.gusrubin.lab.meuwebservice;

import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "com.mkyong.ws.HelloWorld")
public class MeuWebServiceImpl implements MeuWebService {

	public String getHelloWorldAsString() {
		return "Hello World JAX-WS";
	}

}
