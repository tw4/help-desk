package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.ConfigServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.ConfigDAO;
import buzzspire.helpdesk.dto.request.Config.AddConfigRequest;
import buzzspire.helpdesk.entities.concreates.Config;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.TicketStatus;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigManager implements ConfigServices {
    // inject ConfigDAO and JwtTokenProvider
    private final ConfigDAO configDAO;
    private JwtTokenProvider jwtTokenProvider;

    // constructor injection
    public ConfigManager(ConfigDAO configDAO, JwtTokenProvider jwtTokenProvider) {
        this.configDAO = configDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // add method
    @Override
    public Result add(String token, AddConfigRequest addConfigRequest) {
        if (jwtTokenProvider.validateToken(token) && jwtTokenProvider.getRoleFromToken(token).equals(RoleEnum.ADMIN.toString())){
           try{
               configDAO.save(Config.builder()
                       .closeTicketStatus(TicketStatus.builder().id(addConfigRequest.getCloseTicketStatusId()).build())
                       .openTicketStatus(TicketStatus.builder().id(addConfigRequest.getOpenTicketStatusId()).build()).build());
           } catch (Exception e){
               return new Result(false, "Config could not be added");
              }
        } else {
            return new Result(false, "access denied");
        }
        return new Result(true, "Config added");
    }

    @Override
    public ResultData<List<Config>> getAll(String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if (role.equals(RoleEnum.ADMIN.toString())){
            return new ResultData<>(configDAO.findAll(), "config list", true);
        } else {
            return new ResultData<>(null, "access denied", false);
        }
    }
}
