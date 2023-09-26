package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.SupportServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.SupportDAO;
import buzzspire.helpdesk.entities.concreates.Support;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Service;

@Service
public class SupportManager implements SupportServices {
    private final SupportDAO supportDAO;
    private final String secretKey =  "secretKey";

    public SupportManager(SupportDAO supportDAO) {
        this.supportDAO = supportDAO;
    }

    @Override
    public Result addSupport(buzzspire.helpdesk.dto.request.SupportRequest supportRequest) {
        if (!supportRequest.getSecretKey().equals(secretKey)){
            return new Result(false,"wrong secret key");
        }
        try {
            supportDAO.save(Support.builder().user(User.builder().id(supportRequest.getUserId()).build()).build());
        }catch (Exception e){
            return new Result(false,"Support not added");
        }
        return new Result(true,"Support added");
    }

    @Override
    public Result removeSupport(buzzspire.helpdesk.dto.request.SupportRequest supportRequest) {
        if (!supportRequest.getSecretKey().equals(secretKey)){
            return new Result(false,"wrong secret key");
        }
        Support support = supportDAO.findByUserId(supportRequest.getUserId());
        try {
            supportDAO.delete(support);
        }catch (Exception e){
            return new Result(false,"Support not removed");
        }
        return new Result(true,"Support removed");
    }

    @Override
    public ResultData<Boolean> AuthSupport(long userId) {
        try {
            return new ResultData<>(true,"Support authenticated",supportDAO.existsByUserId(userId));
        }catch (Exception e){
            return new ResultData<>(false,"Support not authenticated",false);
        }
    }
}
