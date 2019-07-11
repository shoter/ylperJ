package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import com.shoter.ylper.core.Users.UserRepository;

import javax.persistence.NoResultException;
import java.util.List;

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

    public void remove(long id) {
        demandRepository.remove(id);
    }

    public Demand get(long demandId) {
        try {
            return demandRepository.getDemand(demandId);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<CarFeature> getFeaturesForDemand(long demandId) {
        return demandRepository.getFeaturesForDemand(demandId);
    }
}
