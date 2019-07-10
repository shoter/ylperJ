package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import com.shoter.ylper.core.Users.UserRepository;

public class DemandServiceImpl extends ServiceBase<Demand> implements DemandService {

    private DemandRepository demandRepository;
    private UserRepository userRepository;

    public DemandServiceImpl(DemandRepository demandRepository, UserRepository userRepository)
    {
        this.demandRepository = demandRepository;
        this.userRepository = userRepository;
    }

    public MethodResult canAdd(Demand demand) {
        MethodResult result = new MethodResult();

        if(userRepository.userExists(demand.getUser().getId()) == false)
        {
            result.addError(DemandErrors.userDoeNotExist);
        }

        if(demand.getDesiredDropDateTime() != null && demand.getDesiredStartDateTime() != null &&
                demand.getDesiredDropDateTime()
                .before(demand.getDesiredStartDateTime()))
        {
            result.addError(DemandErrors.dropTimeBeforeStart);
        }

        return MethodResult.merge(result, validate(demand));
    }

    public void add(Demand demand) {
        demandRepository.add(demand);
    }

    public MethodResult canRemove(long id) {

        if(demandRepository.exists(id) == false)
        {
            return new MethodResult(DemandErrors.demandDoesNotExist);
        }

        return new MethodResult();
    }

    public void Remove(long id) {

    }
}
