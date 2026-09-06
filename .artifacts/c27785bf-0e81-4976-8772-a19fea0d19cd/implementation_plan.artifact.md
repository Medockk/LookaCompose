# Исправление ошибки сборки Hilt в модуле app (AGP 9.x)

Ошибка `Android BaseExtension not found` при применении плагина Hilt вызвана тем, что в Android Gradle Plugin (AGP) версии 9.0 и выше был удален устаревший класс `BaseExtension`. Плагин Hilt (версия 2.57.1) все еще пытается его использовать, что приводит к сбою.

## User Review Required

> [!IMPORTANT]
> Проект использует экспериментальные/будущие версии AGP (9.3.2) и Kotlin (2.4.10). Для стабильной работы рекомендуется использовать стабильные версии, но если вы хотите продолжать работу на текущих версиях, необходимо обновить Hilt и настроить обратную совместимость DSL.

## Proposed Changes

### [Component Name] Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/gradle/libs.versions.toml)
*   Обновить версию `hilt` до `2.60.1` (более совместима с AGP 9.x).
*   Обновить версию `ksp` до `2.4.10-1.0.26` (или аналогичную, соответствующую версии Kotlin 2.4.10) для корректной работы процессора аннотаций.

#### [MODIFY] [gradle.properties](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/gradle.properties)
*   Добавить флаг `android.newDsl=false` для временного включения поддержки устаревшего DSL (и класса `BaseExtension`), пока все плагины (включая Hilt) не будут полностью адаптированы к AGP 9.x.

#### [MODIFY] [HiltConventionPlugin.kt](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/build-logic/convention/src/main/kotlin/HiltConventionPlugin.kt)
*   Перенести добавление зависимостей внутрь блоков `withPlugin`, чтобы зависимости добавлялись только тогда, когда Hilt плагин действительно применен.
*   Использовать более надежный способ применения плагина.

## Verification Plan

### Automated Tests
- Выполнить команду `./gradlew :app:help` для проверки успешной конфигурации проекта.
- Попробовать выполнить сборку `./gradlew :app:assembleDebug`.

### Manual Verification
- Проверить, что в IDE исчезли ошибки синхронизации Gradle.
