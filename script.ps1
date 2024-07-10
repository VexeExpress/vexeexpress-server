$PROJECT = "vexeexpress"
$PROJECT_CONTAINER = "${PROJECT}-server-container-1"
$PROJECT_IMAGE = "${PROJECT}-server-image"
$PROJECT_NETWORK = "${PROJECT}_network"

function access_docker {
    docker exec -it $PROJECT_CONTAINER sh
}

function access_database_docker {
    docker exec -it $PROJECT_CONTAINER psql -U postgres
}

function build_docker {
    docker buildx build . -f Dockerfile -t $PROJECT_IMAGE --no-cache
}

function build_dev_docker {
    docker buildx build . -f Dockerfile-dev -t $PROJECT_IMAGE --no-cache
}

function build_cache_docker {
    docker buildx build . -f Dockerfile -t $PROJECT_IMAGE
}

function build_dev_cache_docker {
    docker buildx build . -f Dockerfile-dev -t $PROJECT_IMAGE
}

function log_container_docker {
    docker logs $PROJECT_CONTAINER
}

function rebuild_all_docker {
    docker compose -p $PROJECT down
    docker network rm $PROJECT_NETWORK
    docker rmi $PROJECT_IMAGE
    docker buildx build . -t $PROJECT_IMAGE --no-cache
    docker compose -p $PROJECT up -d
}

function rebuild_all_cache_docker {
    docker compose -p $PROJECT down
    docker network rm $PROJECT_NETWORK
    docker rmi $PROJECT_IMAGE
    docker buildx build . -t $PROJECT_IMAGE
    docker compose -p $PROJECT up -d
}

function remove_all_docker {
    docker compose -p $PROJECT down
    docker network rm $PROJECT_NETWORK
    docker rmi $PROJECT_IMAGE
}

function remove_container_docker {
    docker compose -p $PROJECT down
}

function remove_image_docker {
    docker rmi $PROJECT_IMAGE
}

function remove_network_docker {
    docker network rm $PROJECT_NETWORK
}

function start_docker {
    docker compose -p $PROJECT up -d
}

function stop_docker {
    docker compose -p $PROJECT stop
}

function print_list {
    Write-Host "Pass wrong arguments! Here is list of arguments for docker script"
    Write-Host "`taccess                      : access container"
    Write-Host "`taccess-database             : access database container"
    Write-Host "`tbuild                       : build without cache"
    Write-Host "`tbuild-cache                 : build with cache"
    Write-Host "`tbuild-dev                   : build dev without cache"
    Write-Host "`tbuild-dev-cache             : build dev with cache"
    Write-Host "`tlog                         : log docker container"
    Write-Host "`trebuild-all                 : remove, rebuild and start"
    Write-Host "`trebuild-all-cache            : rebuild all with cache"
    Write-Host "`tremove-all                  : remove all (container, network, image)"
    Write-Host "`tremove-container            : remove container"
    Write-Host "`tremove-image                : remove image"
    Write-Host "`tremove-network              : remove network"
    Write-Host "`tstart                       : start docker compose (docker compose up -d)"
    Write-Host "`tstop                        : stop docker compose (docker compose stop)"
}

if ($args.Count -eq 1) {
    switch ($args[0]) {
        "access" { access_docker }
        "access-database" { access_database_docker }
        "build" { build_docker }
        "build-cache" { build_cache_docker }
        "build-dev" { build_dev_docker }
        "build-dev-cache" { build_dev_cache_docker }
        "log" { log_container_docker }
        "rebuild-all" { rebuild_all_docker }
        "rebuild-all-cache" { rebuild_all_cache_docker }
        "remove-all" { remove_all_docker }
        "remove-container" { remove_container_docker }
        "remove-network" { remove_network_docker }
        "remove-image" { remove_image_docker }
        "start" { start_docker }
        "stop" { stop_docker }
        Default { print_list }
    }
} else {
    print_list
}
