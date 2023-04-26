package com.example.scheduler

interface Navigable {
    enum class Destination {
        List, Add
    }
    fun navigate(to: Destination)

    //FIXME - zrobić odpowiednie przekierowanie do fragmentu edycji
    // po naciśnięciu na konkretny element listy
    fun navigateToEdit(to: Navigable.Destination,
                       name : String,
                       ingredients : List<String>,
                       rsId : Int)
}