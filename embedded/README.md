# Embedded — ESP32 firmware (Wokwi simulated)

Microcontroller firmware, starting **simulated** in **[Wokwi](https://wokwi.com)** (free, in-browser
ESP32/Arduino simulator — nothing to install). Buy a real board (~$15) only if it grips me.

## Setup
1. Make a free account at [wokwi.com](https://wokwi.com).
2. New project → **ESP32** → **Arduino** framework. Start from the blink example.
3. (Optional later) VS Code + PlatformIO + the Wokwi extension for a "real" workflow.

Save Wokwi project links / exported code here as I go (`diagram.json` + `sketch.ino`).

## Plan (3 weeks, ~6–10 hrs/wk)
| Week | Build | Deliverable |
|------|-------|-------------|
| 1 | Blink LED; read a button; print over serial | LED blinks; button state on serial monitor |
| 2 | Read analog sensor (potentiometer/temp via ADC); PWM output (LED brightness / servo) | **MINIMUM DONE: a sensor controls an output in real time** |
| 3 | I²C peripheral (OLED display); small control loop (servo follows potentiometer) | Sensor → logic → actuator + display, as one system |

Stretch: bare-metal register writes (toggle GPIO via registers, no Arduino libs). If it grips me →
buy an ESP32/Pico and redo week 2 on real hardware (shipping to Iraq: 1–3 wks).

## What I learned / would do next
- (write at the end)
