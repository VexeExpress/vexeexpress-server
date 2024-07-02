PROJECT=vexeexpress
PROJECT_CONTAINER=${PROJECT}-server-container-1
PROJECT_IMAGE=${PROJECT}-server-image
PROJECT_NETWORK=${PROJECT}_network

access_docker() {
	docker exec -it ${PROJECT_CONTAINER} sh
}

access_database_docker() {
	docker exec -it ${PROJECT_CONTAINER} psql -U postgres
}

build_docker() {
	docker buildx build . -f Dockerfile -t ${PROJECT_IMAGE} --no-cache
}

build_dev_docker() {
	docker buildx build . -f Dockerfile-dev -t ${PROJECT_IMAGE} --no-cache
}

build_cache_docker() {
	docker buildx build . -f Dockerfile -t ${PROJECT_IMAGE}
}

build_dev_cache_docker() {
	docker buildx build . -f Dockerfile-dev -t ${PROJECT_IMAGE}
}

log_container_docker() {
	docker logs ${PROJECT_CONTAINER}
}

rebuild_all_docker() {
	docker compose -p ${PROJECT} down
	docker network rm ${PROJECT_NETWORK}
	docker rmi ${PROJECT_IMAGE}
	docker buildx build . -t ${PROJECT_IMAGE} --no-cache
	docker compose -p ${PROJECT} up -d
}

rebuild_all_cache_docker() {
	docker compose -p ${PROJECT} down
	docker network rm ${PROJECT_NETWORK}
	docker rmi ${PROJECT_IMAGE}
	docker buildx build . -t ${PROJECT_IMAGE}
	docker compose -p ${PROJECT} up -d
}

remove_all_docker() {
	docker compose -p ${PROJECT} down
	docker network rm ${PROJECT_NETWORK}
	docker rmi ${PROJECT_IMAGE}
}

remove_container_docker() {
	docker compose -p ${PROJECT} down
}

remove_image_docker() {
	docker rmi ${PROJECT_IMAGE}
}

remove_network_docker() {
	docker network rm ${PROJECT_NETWORK}
}

start_docker() {
	docker compose -p ${PROJECT} up -d
}

stop_docker() {
	docker compose -p ${PROJECT} stop
}

print_list() {
	echo "Pass wrong arguments! Here is list of arguments for docker script"
	echo -e "\taccess                      : access container"
	echo -e "\taccess-database             : access database container"
	echo -e "\tbuild                       : build without cache"
	echo -e "\tbuild-cache                 : build without cache"
	echo -e "\tlog                         : log docker container"
	echo -e "\trebuild-all                 : remove, rebuild and start"
	echo -e "\tremove-all                  : remove all (container, network, image)"
	echo -e "\tremove-all-cache            : remove all with cahce (container, network, image)"
	echo -e "\tremove-container            : remove container"
	echo -e "\tremove-image                : remove image"
	echo -e "\tremove-network              : remove network"
	echo -e "\tstart                       : start docker compose (docker compose up -d)"
	echo -e "\tstop                        : stop docker compose (docker compose stop)"
}

if [ $# -eq 1 ]; then
	if [ "$1" == "access" ]; then
		access_docker
	elif [ "$1" == "access-database" ]; then
		access_database_docker
	elif [ "$1" == "build" ]; then
		build_docker
	elif [ "$1" == "build-cache" ]; then
		build_cache_docker
	elif [ "$1" == "build-dev" ]; then
		build_dev_docker
	elif [ "$1" == "build-dev-cache" ]; then
		build_dev_cache_docker
	elif [ "$1" == "log" ]; then
		log_container_docker
	elif [ "$1" == "rebuild-all" ]; then
		rebuild_all_docker
	elif [ "$1" == "rebuild-all-cache" ]; then
		rebuild_all_cache_docker
	elif [ "$1" == "remove-all" ]; then
		remove_all_docker
	elif [ "$1" == "remove-container" ]; then
		remove_container_docker
	elif [ "$1" == "remove-network" ]; then
		remove_network_docker
	elif [ "$1" == "remove-image" ]; then
		remove_image_docker
	elif [ "$1" == "remove-image" ]; then
		remove_image_docker
	elif [ "$1" == "start" ]; then
		start_docker
	elif [ "$1" == "stop" ]; then
		stop_docker
	else
		print_list
	fi
else
	print_list
fi