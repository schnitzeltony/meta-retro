SUMMARY = "D1X-Rebirth is a Source Port of the Descent Game"
HOMEPAGE = "http://www.dxx-rebirth.com/"
SECTION = "games"
LICENSE = "DXX-Rebirth"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=022f1bfd6fd458067b51b10cd3186e78"

DEPENDS = "python-scons-native libsdl libsdl-mixer physfs p7zip-native"

SRC_URI = " \
    http://www.dxx-rebirth.com/download/dxx/${BPN}_${PV}-src.tar.gz \
    http://www.dxx-rebirth.com/download/dxx/content/descent-mac-demo.zip;name=data \
    http://www.descent2.de/files/sound/hires-sounds.7z;name=sound \
    http://www.dxx-rebirth.com/download/dxx/res/d1xr-sc55-music.dxa;name=music;unpack=0 \
"
SRC_URI[md5sum] = "8f43e66191fdedd0c7ff9278719accf1"
SRC_URI[sha256sum] = "5befc8a2ab1159fdb4d4141ba9f0e229450592d5ada209e832101da5b0b3e38e"
SRC_URI[data.md5sum] = "010815be5d87d61150a938ad593102fe"
SRC_URI[data.sha256sum] = "17d2fdefc06a96a50f54d4eba745835b619115e5327ddc3d23052bf4ec0e3300"
SRC_URI[sound.md5sum] = "474fdf1c1f8d2bbbc68f271cb9aa28b8"
SRC_URI[sound.sha256sum] = "39cab9e2aed4c6ec9e3b811bbdf2c880d4acd87e4c66a9e1f80f1b25aef5ef6b"
SRC_URI[music.md5sum] = "bf54b45a648052b2531bbb95c001403b"
SRC_URI[music.sha256sum] = "b27f7b9dc5f9c2744402c56c9499dfd9503c17e73a2a5223e745529d7867962f"

S = "${WORKDIR}/${BPN}_${PV}-src"

do_compile() {
    scons opengl=0 prefix=${D}${prefix} sharepath=${datadir}/${BPN}
}

do_install() {
    install -d ${D}${bindir}
    install ${BPN} ${D}${bindir}

    install -d ${D}${datadir}/applications
    install ${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install ${BPN}.xpm ${D}${datadir}/pixmaps

    install -d ${D}${datadir}/${BPN}
    install ../descent.hog ../descent.pig ../d1xr-sc55-music.dxa ${D}${datadir}/${BPN}
}

RRECOMMENDS_${PN} = "tremor"
