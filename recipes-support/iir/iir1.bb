SUMMARY = "DSP IIR realtime filter library written in C++"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3e4bda4204237f84ed771428f1b0a656"

SRC_URI = "git://github.com/berndporr/iir1.git;protocol=https;branch=master"
SRCREV = "f5eaccc87c437d7ca3d050bdfe0783523dbb9d0e"
PV = "1.9.3"
S = "${WORKDIR}/git"

inherit cmake pkgconfig
