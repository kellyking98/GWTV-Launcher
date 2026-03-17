# GWTV Minimal Launcher

A minimal Android TV launcher for IPTV box deployments.

## Features

- Auto-launches GWTV app on startup
- Only two accessible functions: Launch GWTV and WiFi Settings
- Blocks access to all other apps and settings
- Custom background support
- Configurable via ADB

## Building

### GitHub Actions (Easiest):
1. Upload this project to GitHub
2. Go to Actions tab
3. Run "Build APK" workflow
4. Download APK from artifacts

### Android Studio:
1. Open this project in Android Studio
2. Build > Build APK
3. APK at: app\build\outputs\apk\release\

## Deployment

See adb-setup-script.bat for automated deployment.

## Package Details

- Package: com.minimal.iptvlauncher
- IPTV Package: com.andyhax.haxsplash
- Min SDK: 21
- Target SDK: 33
