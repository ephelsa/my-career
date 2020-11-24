VERSION := $(shell ./tag.sh)

release:
	@git tag -a $(VERSION)
	@git push origin $(VERSION)

.PHONY: version
