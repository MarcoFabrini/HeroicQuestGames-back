package com.hqc.beck.services.interfaces;

import com.hqc.beck.request.BoardGameRequest;

public interface IBoardGameService {
    void create(BoardGameRequest req) throws Exception;

    void update(BoardGameRequest req) throws Exception;

}// interface
