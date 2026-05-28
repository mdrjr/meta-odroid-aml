#!/bin/sh
# ODROID-C4 (Amlogic SM1) HDMI audio routing.

CARD=0

amixer -c ${CARD} sset 'FRDDR_A SINK 1 SEL' 'OUT 1' || true
amixer -c ${CARD} sset 'FRDDR_A SRC 1 EN'   'on'    || true
amixer -c ${CARD} sset 'TDMOUT_B SRC SEL'   'IN 0'  || true
amixer -c ${CARD} sset 'TOHDMITX I2S SRC'   'I2S B' || true
amixer -c ${CARD} sset 'TOHDMITX'           'on'    || true

exit 0
