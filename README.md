# BibleLock

![CI](https://github.com/masukutemogo/BibleLock/actions/workflows/ci.yml/badge.svg)

An Android app that locks the phone each morning until the user has read a Bible passage and answered a short recall question. Calls and alarms still come through, and Do Not Disturb switches on once reading starts.

Built by Pishon Group.

## Status

Early development. Currently implemented:

- [x] `core-domain`: reading validation and recall-answer checking (pure Kotlin, unit-tested)
- [x] CI pipeline: build and test on every push
- [ ] Data layer (Room, encrypted storage)
- [ ] Lock and enforcement (overlay, accessibility service)
- [ ] Reading and verification UI
- [ ] Security hardening and threat model

## Architecture

Multi-module Clean Architecture. The domain module has no Android dependencies, so the app's rules can be tested without an emulator.

```
app/            Application entry point
core-domain/    Models and use cases (pure Kotlin)
```

More modules will be added as each layer is built.

## Running the tests

```
./gradlew :core-domain:test
```

## Scripture text

Passages are from the World English Bible (WEB), which is in the public domain.