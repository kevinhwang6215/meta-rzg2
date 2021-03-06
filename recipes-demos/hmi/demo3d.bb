LICENSE = "CLOSED"
DESCRIPTION = "Demo3D"

DEPENDS += " mesa gles-user-module weston wayland freetype libpng gstreamer1.0 gstreamer1.0-plugins-base"

SRC_URI = " \	
 file://Demo3D.tar.xz \
 file://Renesas.mp4 \
 file://0001-HMI-demos-demo3d-Porting-demo3d-to-VLP2.1-and-HMI-de.patch \
 file://0002-demo3d-add-h264parse-to-correct-pipeline-play-video-.patch \
 file://0003-Demo3D-Gst-change-sync-property-to-true.patch \
 file://0004-Demo3D-Video-scale-height-to-480-to-fix-greenline-is.patch \
 file://0005-Demo3D-Video-use-vspfilter-to-scaling-element.patch \
 file://0007-Check-input-size-before-set-to-window.patch \
 file://0008-Change-offset-to-fix-with-text.patch \
 file://0009-HMI-demos-demo3d-Support-for-running-demo3d-in-fulls.patch \
 file://0010-hmi-demos-demo3d-Support-for-CSI-camera.patch \
 file://0011-Demo3D-change-the-way-to-get-camera-device.patch \
 file://0012-demo3d-add-feature-to-select-camera-when-display-cam.patch \
 file://0013-demo3d-keep-all-button-of-texture-mapping-available-.patch \
"

S = "${WORKDIR}/Demo3D"

SRC = "${THISDIR}/Demo3D"

inherit pkgconfig

INSANE_SKIP_${PN} = "already-stripped"

RDEPENDS_libweston-2_append = " gles-user-module "

do_patch() {
    cd ${WORKDIR}/Demo3D
    git init
    git apply ../*.patch
}

do_compile() {
    cd ${WORKDIR}/Demo3D
    make
}

do_install() {
    install -d ${D}/home/root/Demo3D
    cp -Rf ${WORKDIR}/Demo3D/data/* ${D}/home/root/Demo3D
    install ${WORKDIR}/Demo3D/Demo3D ${D}/home/root/Demo3D/
    install ${WORKDIR}/Renesas.mp4 ${D}/home/root/Demo3D/
}

RDEPENDS_${PN} += "gles-user-module"

FILES_${PN}-dev = "${libdir}/* ${includedir}"

FILES_${PN} = "/home/root/Demo3D/* \	
               /home/root/Demo3D/pics* \
"
FILES_${PN}-dbg += " \
 /home/root/Demo3D/.debug/* \
"
