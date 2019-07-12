package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Demands.DemandService;
import com.shoter.ylper.core.Results.MethodResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.ParseException;
import java.util.Set;


@Controller
@RequestMapping(value = "/demands")
public class DemandController extends ControllerBase {
    private DemandService demandService;
    private EntityFactory entityFactory;

    public DemandController(DemandService demandService, Validator validator, EntityFactory entityFactory)
    {
        super(validator);
        this.demandService = demandService;
        this.entityFactory = entityFactory;
    }

    @GetMapping("/{demandId}")
    public @ResponseBody
    ResponseEntity<DemandModel> getDemand(@PathVariable long demandId)
    {
        Demand demand = demandService.get(demandId);

        if(demand == null)
            return response(404, null);

        return response(200, new DemandModel(demand));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<DemandModel> createDemand(@RequestBody DemandModel model) throws ParseException {
        Set<ConstraintViolation<DemandModel>> violations = validator.validate(model);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        Demand demand = entityFactory.create(Demand.class, model);

        MethodResult result = demandService.canAdd(demand);
        if(result.isFailure())
        {
            return response400(result);
        }

        demandService.add(demand);
        demand = demandService.get(demand.getId());

        return response(200, new DemandModel(demand));
    }

    @DeleteMapping("/{demandId}")
    public @ResponseBody ResponseEntity deleteDemand(@PathVariable long demandId)
    {
        MethodResult result = demandService.canRemove(demandId);
        if(result.isFailure())
            return response400(result);

        demandService.remove(demandId);
        return response(200, null);
    }
}
