# Исправление Compose Preview в модуле :core:ui и оптимизация AGP 9.x

Ошибка `Missing ComposeViewAdapter` при рендеринге Preview обычно указывает на отсутствие библиотеки `ui-tooling` в classpath или на проблемы с подключением Compose Compiler. В AGP 9.x и Kotlin 2.x механизмы подключения изменились, что требует точной настройки конвеншн-плагинов.

## User Review Required

> [!IMPORTANT]
> Мы переходим на использование Java 17 в конвеншн-плагинах, так как современные версии Compose и Android SDK 37 требуют этого. Также мы явно прописываем `ui-tooling` для всех конфигураций в библиотечных модулях, чтобы IDE могла корректно находить классы рендерера.

## Proposed Changes

### [Component Name] Build Logic

#### [MODIFY] [AndroidComposeConventionPlugin.kt](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/build-logic/convention/src/main/kotlin/AndroidComposeConventionPlugin.kt)
*   Вернуть `ui-tooling` в `debugImplementation`, но добавить `platform(bom)` для этой конфигурации.
*   Убедиться, что плагин `org.jetbrains.kotlin.plugin.compose` применяется корректно.
*   Добавить `androidx-compose-ui-tooling` в `implementation` временно для проверки, если `debugImplementation` не подхватывается IDE.

#### [MODIFY] [AndroidLibraryConventionPlugin.kt](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/build-logic/convention/src/main/kotlin/AndroidLibraryConventionPlugin.kt)
*   Убедиться, что Java 17 установлена для `compileOptions`.

#### [MODIFY] [core:ui/build.gradle.kts](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/core/ui/build.gradle.kts)
*   Очистить лишние зависимости, перенеся их обратно в конвеншн-плагин для чистоты архитектуры.

### [Component Name] UI Components

#### [MODIFY] [PrimaryLoadingButton.kt](file:///C:/Users/S/AndroidStudioProjects/LookaCompose/core/ui/src/main/java/com/s/looka/core/ui/components/button/PrimaryLoadingButton.kt)
*   Восстановить исходный код Preview с использованием `ApplicationLookaTheme`.

## Verification Plan

### Automated Tests
- Запустить `render_compose_preview` для `PrimaryLoadingButtonPreview`.
- Выполнить сборку модуля `:core:ui:assembleDebug`.

### Manual Verification
- Проверить отображение Preview в Android Studio.
