load('ext://restart_process', 'docker_build_with_restart')

local_resource(
  'example-app-jar',
  'mvn clean install && ' +
  'rm -rf build/jar-tmp && ' +
  'unzip -o target/app-2.0.1.jar -d build/jar-tmp && ' +
  'rsync --delete --inplace --checksum -r build/jar-tmp/ build/jar',
  deps = ['src', 'pom.xml'],
)

docker_build_with_restart('tilt-example-image',
    './build/jar',
    dockerfile='./Dockerfile',
    entrypoint=['java', '-noverify', '-cp', '.:./lib/*', 'org.example.app.ExampleApp'],
    live_update = [
          sync('./build/jar/BOOT-INF/classes', '/workspace'),
          sync('./build/jar/BOOT-INF/lib', '/workspace/lib'),
          sync('./build/jar/META-INF', '/workspace/META-INF'),
        ],
)

k8s_yaml('./manifest/manifest.yaml')

k8s_resource('tilt-example', port_forwards=["8080:8080"],
            resource_deps=["example-app-jar"])



