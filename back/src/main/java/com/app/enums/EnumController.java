package com.app.enums;

import com.app.system.Result;
import com.app.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/roles")
    public Result getRoles() {
        Map<String, String> res = new HashMap<>();
        for (Role i : Role.values()) res.put(i.name(), i.getName());
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Roles",
                res
        );
    }

    @GetMapping("/clientCategories")
    public Result getClientCategories() {
        Map<String, String> res = new HashMap<>();
        for (ClientCategory i : ClientCategory.values()) res.put(i.name(), i.getName());
        return new Result(
                true,
                StatusCode.SUCCESS,
                "ClientCategories",
                res
        );
    }

    @GetMapping("/clientGenders")
    public Result getClientGenders() {
        Map<String, String> res = new HashMap<>();
        for (ClientGender i : ClientGender.values()) res.put(i.name(), i.getName());
        return new Result(
                true,
                StatusCode.SUCCESS,
                "ClientGenders",
                res
        );
    }

    @GetMapping("/tourSeasons")
    public Result getTourSeasons() {
        Map<String, String> res = new HashMap<>();
        for (TourSeason i : TourSeason.values()) res.put(i.name(), i.getName());
        return new Result(
                true,
                StatusCode.SUCCESS,
                "TourSeasons",
                res
        );
    }

    @GetMapping("/tourTypes")
    public Result getTourTypes() {
        Map<String, String> res = new HashMap<>();
        for (TourType i : TourType.values()) res.put(i.name(), i.getName());
        return new Result(
                true,
                StatusCode.SUCCESS,
                "TourTypes",
                res
        );
    }

}
