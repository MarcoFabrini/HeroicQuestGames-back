package com.hqc.back.services.interfaces;

import com.hqc.back.request.ConsoleRequest;

public interface IConsoleService {
    void create(ConsoleRequest req) throws Exception;

    void update(ConsoleRequest req) throws Exception;
}// interface
