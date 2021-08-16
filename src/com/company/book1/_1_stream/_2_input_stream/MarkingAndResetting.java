package com.company.book1._1_stream._2_input_stream;

public class MarkingAndResetting {

    // mark(int readAheadLimit)  -> In order to reread data mark the current position in the stream with the mark()
    // reset() -> reset the stream to the mark position using the reset() method. -> Subsequent rad then return to the marked position
        // can not reset as far back as you like.readAheadLimit   the number of bytes you can read from the mark  and still reset
        // if readAheadLimit is 20. Now the offset is 10 and we marked it so 10 -> 30 we can reset to back to. But if we read to the 31 offset
        // reset -> throw IOE exception. Only one mark in a stream at given time
    // markSupported():not every Stream are supported this so we can check

    // An object-oriented approach would embed this in the
    //type system through interfaces and classes so that it could all be
    //checked at compile time




}
