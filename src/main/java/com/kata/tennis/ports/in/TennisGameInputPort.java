package com.kata.tennis.ports.in;

import com.kata.tennis.domain.exception.TennisException;

public interface TennisGameInputPort {
    void playGame() throws TennisException;
}
