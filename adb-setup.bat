@echo off
echo ==========================================
echo   GWTV Android TV Box Setup
echo ==========================================
echo.
if "%1"=="" (
    echo ERROR: No IP address provided
    echo Usage: adb-setup.bat <IP_ADDRESS>
    pause
    exit /b 1
)

set DEVICE_IP=%1

echo Connecting to device...
adb disconnect >nul 2>&1
adb connect %DEVICE_IP%:5555
timeout /t 2 /nobreak >nul

echo Installing GWTV Launcher...
adb install -r -d gwtv-launcher.apk

echo Installing GWTV App...
adb install -r -d GWTV_v4_0_5.apk

echo Setting as default launcher...
adb shell cmd package set-home-activity com.minimal.iptvlauncher/com.minimal.iptvlauncher.MainActivity

echo Disabling stock launcher...
adb shell pm disable-user --user 0 com.google.android.tvlauncher 2>nul

echo Setup complete!
echo.
set /p REBOOT=Reboot device now? (Y/N): 
if /i "%REBOOT%"=="Y" adb reboot

adb disconnect >nul 2>&1
pause
