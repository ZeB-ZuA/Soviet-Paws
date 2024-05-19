package com.udistrital.soviet_paws.models

enum class PetType(val type: String, val breeds: List<String>) {
    DOGS("Dogs", listOf("Labrador Retriever", "Pastor Alemán", "Golden Retriever", "Bulldog Francés", "Beagle")),
    CATS("Cats", listOf("Persa", "Maine Coon", "Siamese", "Ragdoll", "Bengalí")),
    RABBITS("Rabbits", listOf("Conejo de Nueva Zelanda", "Conejo Californiano", "Conejo Satinado", "Conejo Rex", "Conejo Holandés")),
    POULTRY("Poultry", listOf("Leghorn", "Rhode Island Red", "Sussex", "Plymouth Rock", "Wyandotte")),
    HAMSTERS("Hamsters", listOf("Hámster Sirio", "Hámster Roborovski", "Hámster Ruso", "Hámster Chino", "Hámster Campbell")),
    FISHES("Fishes", listOf("Pez Betta", "Pez Ángel", "Pez Disco", "Pez Payaso", "Pez Tetra Neón")),
    EXOTIC_REPTILES("Exotic Reptiles", listOf("Pitón de Boelen", "Boa esmeralda", "Pitón verde de árbol", "Boa arco iris brasileña", "Camaleón pantera")),
    INVERTEBRATES("Invertebrates", listOf("Caracol manzana", "Cangrejo ermitaño", "Camarón cereza", "Medusa de luna", "Estrella de mar común")),
    HORSES("Horses", listOf("Caballo Árabe", "Caballo de pura sangre inglés", "Caballo Andaluz", "Caballo Appaloosa", "Caballo Morgan")),
    COWS("Cows", listOf("Holstein", "Angus", "Hereford", "Jersey", "Charolais")),
    OTHERS("Others", listOf());
}
