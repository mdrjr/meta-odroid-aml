#!/bin/sh
# ODROID-N2 / N2+ / N2L (Amlogic G12B) audio routing.
#

CARD=0

# HDMI
amixer -c ${CARD} sset 'FRDDR_A SINK 1 SEL' 'OUT 1' || true
amixer -c ${CARD} sset 'FRDDR_A SRC 1 EN'   'on'    || true
amixer -c ${CARD} sset 'TDMOUT_B SRC SEL'   'IN 0'  || true
amixer -c ${CARD} sset 'TOHDMITX I2S SRC'   'I2S B' || true
amixer -c ${CARD} sset 'TOHDMITX'           'on'    || true

# Analog ACODEC
amixer -c ${CARD} sset 'FRDDR_B SINK 1 SEL'  'OUT 2' || true
amixer -c ${CARD} sset 'FRDDR_B SRC 1 EN'    'on'    || true
amixer -c ${CARD} sset 'TDMOUT_C SRC SEL'    'IN 1'  || true
amixer -c ${CARD} sset 'TOACODEC SRC'        'I2S C' || true
amixer -c ${CARD} sset 'TOACODEC OUT EN'     'on'    || true
amixer -c ${CARD} sset 'TOACODEC Lane Select' '0'    || true
amixer -c ${CARD} sset 'ACODEC'              '255'   || true

# SPDIF
amixer -c ${CARD} sset 'FRDDR_C SINK 1 SEL'  'OUT 3' || true
amixer -c ${CARD} sset 'FRDDR_C SRC 1 EN'    'on'    || true
amixer -c ${CARD} sset 'SPDIFOUT SRC SEL'    'IN 2'  || true

exit 0
