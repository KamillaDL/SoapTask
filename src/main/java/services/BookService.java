package services;

import models.CreateRequest;
import models.CreateResponse;
import models.FindByNameRequest;
import models.FindByNameResponse;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface BookService {
    public CreateResponse create(@WebParam(name = "createRequest", partName = "createRequest") CreateRequest createRequest);
    public FindByNameResponse findByName(@WebParam(name = "findByNameRequest", partName = "findByNameRequest") FindByNameRequest findByNameRequest);
}
