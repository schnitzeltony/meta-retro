SUMMARY = "Open-source 6502 cross assembler"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f67e705f4eb5064027b8c40ccfdb43e"

inherit autotools-brokensep

SRC_URI = " \
    http://www.floodgap.com/retrotech/xa/dists/${BPN}-${PV}.tar.gz \
    file://0001-Align-Makefile-for-cross-compiling.patch \
"
SRC_URI[md5sum] = "884c3dc5bcc8e8f10b05a6907781623d"
SRC_URI[sha256sum] = "3b97d2fe8891336676ca28ff127b69e997f0b5accf2c7009b4517496929b462a"

BBCLASSEXTEND = "native"
