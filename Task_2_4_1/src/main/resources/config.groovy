import ru.nsu.vetrov.*

def checkerConfig = new CheckerConfig()

// Define tasks
checkerConfig.task {
    setName('Task_1_1_2')
    setMaxMark(10)
    setSoftDeadline('01.05.2024')
    setHardDeadline('10.05.2024')
}

checkerConfig.task {
    setName('Task_1_5_2')
    setMaxMark(20)
    setSoftDeadline('01.06.2024')
    setHardDeadline('10.06.2024')
}

// Define students and groups
checkerConfig.student {
    setName('Student No1')
    setNickname('j4stV')
    setGroup(12345)
}

checkerConfig.student {
    setName('Student No2')
    setNickname('j4stV')
    setGroup(12345)
}

// Return the CheckerConfig object
return checkerConfig
