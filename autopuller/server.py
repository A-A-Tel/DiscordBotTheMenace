import os
import subprocess

def run_command(command):
    process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

    while True:
        output = process.stdout.readline()
        if output == '' and process.poll() is not None:
            break
        if output:
            print(output.strip())

    stderr_output = process.stderr.read()
    if stderr_output:
        print(stderr_output.strip())

    return_code = process.wait()
    if return_code != 0:
        print(f"Command '{' '.join(command)}' failed with return code: {return_code}")

os.chdir("../")
while True:
    run_command(["git", "pull"])
    if os.name == "nt":
        run_command(["gradlew.bat", "shadowJar"])
    else:
        run_command(["./gradlew", "shadowJar"])

    run_command(["java", "-jar", "./build/libs/the-menace-all.jar"])