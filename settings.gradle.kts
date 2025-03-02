rootProject.name = "hexagonal"

// Include submodules
include("app")
include("adapters:web")
include("adapters:persistence")
include("adapters:messaging")
include("boot")
