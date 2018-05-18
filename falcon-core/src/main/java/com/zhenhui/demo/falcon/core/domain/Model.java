package com.zhenhui.demo.falcon.core.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Model extends Attributes {

    private String protocol;

    private String model;

    private List<CommandType> supportedCommands = new ArrayList<>();
}


