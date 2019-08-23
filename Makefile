test:
	@docker-compose run test
.PHONY: test

db:
	@docker-compose run postgres
.PHONY: db
