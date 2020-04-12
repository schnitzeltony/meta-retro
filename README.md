OE layer for emulators and games from 8/16-bit era
--------------------------------------------------


Layer dependencies:
-------------------
see [layer.conf](conf/layer.conf) for dependencies and https://layers.openembedded.org/layerindex/branch/master/layers/ where to find layers


Contributing
------------
* Submit any patches against the `meta-retro` layer by using the GitHub pull-request feature.


Policies
--------
* **Please do not send private emails to maintainer - they will not be answered anymore**. For questions/suggestions.. use [issues](https://github.com/schnitzeltony/meta-retro/issues).
* Pull requests should follow [OE-Styleguide](https://www.openembedded.org/wiki/Styleguide) with the following additions:
  * Use 4 spaces for indentation always
  * For splitting of long list values use four-space indentation on sucessive lines and prefer the closing quote as the first character ([OE-Styleguide](https://www.openembedded.org/wiki/Styleguide) - second example)
  * Pull-requests with patches fixing issues for musl, clang or gold-linker are accepeted only if patches have upstream-status "Applied" or "Backport" and contain a link to the upstream patch.


Maintainers
-----------

Layer maintainer: Andreas Müller <schnitzeltony@gmail.com>
