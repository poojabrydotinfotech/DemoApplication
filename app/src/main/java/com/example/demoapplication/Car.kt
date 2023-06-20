package com.example.demoapplication


class Car{
    private val engine = Engine()

    fun start(type: String) {
        engine.startEngine()

    }

    inner class Car2(private val engine: Engine){
        fun start(type:String){
            engine.startEngine2()

        }

    }

}


