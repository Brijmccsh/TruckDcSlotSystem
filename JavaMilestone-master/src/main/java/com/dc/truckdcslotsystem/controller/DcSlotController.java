package com.dc.truckdcslotsystem.controller;

import com.dc.truckdcslotsystem.model.DcSlot;
import com.dc.truckdcslotsystem.model.DctimeSlotRequest;
import com.dc.truckdcslotsystem.service.DcSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dcSlot")
public class DcSlotController {

    @Autowired
    private DcSlotService dcSlotService;
//
    @DeleteMapping("/delete/dcSlot/{dcSlotId}")
    public ResponseEntity deleteDcSlot(@PathVariable Integer dcSlotId){
        boolean check = dcSlotService.deleteDcSlot(dcSlotId);
        if (check ){
            return  ResponseEntity.status(HttpStatus.OK).body("DELETED");

        }
        return  new ResponseEntity<>( "THERE WAS A PROBLEAM", HttpStatus.BAD_REQUEST );
    }

    @PutMapping("/update/DcSlot/{dcSlotId}")
    public  ResponseEntity updateDcSlot(@RequestBody DcSlot dcSlot , @PathVariable Integer dcSlotId){
        System.out.println(dcSlotId);
        DcSlot dcSlot1  =     dcSlotService.updateDcSlot(dcSlot, dcSlotId);
        if (dcSlot1 != null){
            return  ResponseEntity.status(HttpStatus.OK).body(dcSlot1);
        }
        return  new ResponseEntity<>( "THERE WAS A NO SUCH SLOT", HttpStatus.BAD_REQUEST );
    }

//    @GetMapping("/search/dcSlot/{dcNumber}")
//    public ResponseEntity searchDCSolt(@PathVariable Long dcNumber){
//        List<DcSlot> dc = dcSlotService.searcDcSlot(dcNumber);
//        if (dc.size()==0){
//            return  new ResponseEntity<>( "THERE WAS A NO SUCH SLOT", HttpStatus.BAD_REQUEST );
//        }
//        return  ResponseEntity.status(HttpStatus.OK).body(dc);
//    }

    @PostMapping("/add/dcSlot/{dcNumber}")
    public ResponseEntity addDcSlot(@RequestBody DcSlot dcSlot, @PathVariable Long dcNumber){
        System.out.println("dasdasd");
        boolean dcSlot1 =  dcSlotService.addDcSlot(dcSlot,dcNumber);
        if (!dcSlot1 ){

            return  new ResponseEntity<>( "THERE WAS A PROBLEAM", HttpStatus.BAD_REQUEST );


        }
        return   ResponseEntity.status(HttpStatus.OK).body("ADDED");
    }

    @GetMapping("search/dcTimeSlot/{dctimeTimeSlot}/{dcNumber}")
    public  DcSlot searchDCTimeSlot(@PathVariable String dctimeTimeSlot, @PathVariable Long dcNumber ){
        System.out.println(dctimeTimeSlot);
        System.out.println("dsfkjasdbfsdkbfsfg" +dcSlotService.searchByDCTimeSlot(dctimeTimeSlot,dcNumber));
       if (dcSlotService.searchByDCTimeSlot(dctimeTimeSlot,dcNumber) != null){

             return dcSlotService.searchByDCTimeSlot(dctimeTimeSlot,dcNumber) ;
       }
        return  null;

    }
}
