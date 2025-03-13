package com.hqc.beck.services.interfaces;

import com.hqc.beck.request.ConsoleRequest;

public interface IConsoleService {
    void create(ConsoleRequest req) throws Exception;

    void update(ConsoleRequest req) throws Exception;
}// interface
