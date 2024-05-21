def tasks = [:]
def groups = [:]
def checkpoints = [:]
def settings = [:]

tasks.task1 = [
        id: '2_1_1',
        name: 'Prime Numbers',
        maxPoints: 10,
        softDeadline: '2024-05-01',
        hardDeadline: '2024-05-10'
]

tasks.task2 = [
        id: '2_3_1',
        name: 'Snake Game',
        maxPoints: 20,
        softDeadline: '2024-06-01',
        hardDeadline: '2024-06-10'
]

groups.group1 = [
        name: '22215',
        students: [
                student1: [
                        github: 'j4stV',
                        name: 'Vyacheslav Vetrov',
                        repo: 'https://github.com/j4stV/OOP'
                ]
        ]
]

checkpoints.checkpoint1 = [
        name: 'Midterm',
        date: '2024-06-15'
]

checkpoints.checkpoint2 = [
        name: 'Final',
        date: '2024-07-15'
]

settings.scoringCriteria = 'default'
settings.timeoutStrategy = 'skip'
settings.extraPoints = [
        student1_task2: 1
]

binding.setVariable("tasks", tasks)
binding.setVariable("groups", groups)
binding.setVariable("checkpoints", checkpoints)
binding.setVariable("settings", settings)
