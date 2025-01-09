package com.kata.ports.in;

import com.kata.exception.TennisException;

public interface TennisGameInputPort {
    void playGame() throws TennisException;
}
