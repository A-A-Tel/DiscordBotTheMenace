# Run this script from the terminal from this directory!!!

import os
import subprocess


def run_command(command):
    subprocess.run(command)


os.chdir("../")
while True:
    run_command(["git", "pull"])

    if os.name == "nt":
        run_command(["gradlew.bat", "shadowJar"])
    else:
        run_command(["chmod", "+x", "./gradlew"])
        run_command(["./gradlew", "shadowJar"])

    run_command(["java", "--add-opens", "java.base/java.time=ALL-UNNAMED", "-jar", "./build/libs/the-menace-all.jar"])
