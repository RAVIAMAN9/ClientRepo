package rkj.clientRepo.clientRepo.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.Train;

@FeignClient(name = "TRAIN-SERVICE")
public interface TrainClient {

    @GetMapping("train/{trainNumber}")
    Train getTrainDetails(@PathVariable Integer trainNumber);

}
