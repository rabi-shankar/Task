package com.rabi.task.model

data class Section(
    var id :Int = 0,
    var name : String = "",
    var lectures: List<Lecture>
)

