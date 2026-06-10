#!/bin/sh

DEV=/dev/zram0
SYS=/sys/block/zram0

modprobe zram num_devices=1

# wait for the sysfs node to appear
i=0
while [ ! -e "$SYS" ] && [ $i -lt 50 ]; do i=$((i+1)); sleep 0.1; done

# clean any prior config
swapoff "$DEV" 2>/dev/null || true
echo 1 > "$SYS/reset" 2>/dev/null || true

echo lzo-rle > "$SYS/comp_algorithm" 2>/dev/null || true

MEM_KB=$(awk '/^MemTotal:/{print $2}' /proc/meminfo)
echo $((MEM_KB * 1024)) > "$SYS/disksize"

mkswap "$DEV" >/dev/null 2>&1
swapon -p 100 "$DEV"

exit 0
