statusListener(OnConsoleStatusListener)
def appenderList = ["CONSOLE"]
appender("CONSOLE", ConsoleAppender){
	encoder(PatternLayoutEncoder) {
		pattern = "%d [%thread] %logger{20} [%-5level]: %msg%n"
	}
}
/*Log Levels
TRACE
DEBUG
INFO
WARN
ERROR
*/
//Recommended to keep this at WARN for competition
root(WARN, appenderList)
//logger("org.usfirst.frc.team1758.robot.commands", DEBUG, appenderList)
//logger("org.usfirst.frc.team1758.robot.subsystems", DEBUG, appenderList)
//logger("org.usfirst.frc.team1758.robot.vision", DEBUG, appenderList)
//logger("org.usfirst.frc.team1758.utilities", DEBUG, appenderList)
