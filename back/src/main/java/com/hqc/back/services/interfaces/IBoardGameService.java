package com.hqc.back.services.interfaces;

import com.hqc.back.request.BoardGameRequest;

public interface IBoardGameService {
    void create(BoardGameRequest req) throws Exception;

    void update(BoardGameRequest req) throws Exception;

}// interface
