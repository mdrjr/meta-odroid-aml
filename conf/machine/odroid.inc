SERIAL_CONSOLES := "921600;ttyS0"
KERNEL_IMAGETYPE ?= "Image"

ERROR_QA:remove = "patch-status"
KERNEL_IMAGETYPE = "Image"
IMAGE_FSTYPES ?= " ext4 wic wic.xz"

# glmark2 GPU benchmark: build wayland + drm GLES2 flavors (glmark2-es2-wayland, glmark2-es2-drm)
PACKAGECONFIG:pn-glmark2 = "wayland-gles2 drm-gles2"
