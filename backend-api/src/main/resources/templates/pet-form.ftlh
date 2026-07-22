<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - PetPal</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex flex-col">
    <nav class="bg-white border-b border-gray-200 px-8 md:px-12 py-4 flex justify-between items-center sticky top-0 z-10">
        <a href="/customer/${customerId?c}/dashboard" class="text-xl font-extrabold tracking-tight">PetPal</a>
        <div class="space-x-6 text-sm font-medium">
            <a href="/customer/${customerId?c}/dashboard" class="text-black border-b-2 border-black pb-1">Dashboard</a>
            <a href="/customer/${customerId?c}/providers" class="text-gray-500 hover:text-black transition-colors">Directory</a>
            <a href="/customer/${customerId?c}/profile" class="text-gray-500 hover:text-black transition-colors">My Profile</a>
            <form action="/customer/logout" method="post" class="inline ml-4">
                <button
                    type="submit"
                    class="text-red-600 hover:text-red-800 transition-colors">
                    Log Out
                </button>
            </form>
        </div>
    </nav>

    <main class="max-w-5xl mx-auto px-8 md:px-12 py-12 flex-grow w-full">
        <header class="mb-8">
            <a href="/customer/${customerId?c}/dashboard#pets" class="text-sm text-gray-500 hover:text-black font-semibold mb-4 inline-block">
                &larr; Back to Dashboard
            </a>
            <p class="text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">US-2 · Pet profile</p>
            <h1 class="text-3xl font-extrabold mb-2">${pageTitle}</h1>
            <p class="text-gray-500">Share the information a provider needs to care for your pet safely.</p>
        </header>

        <div class="bg-white border border-gray-200 rounded-2xl p-8 shadow-sm">
            <form method="post" action="${formAction}" class="space-y-6">
                <div class="flex items-center gap-6 pb-6 border-b border-gray-100">
                    <#if pet.imageUrl?? && pet.imageUrl?has_content>
                        <img src="${pet.imageUrl}" alt="${pet.name!"Pet"}" class="w-24 h-24 rounded-full object-cover border border-gray-200">
                    <#else>
                        <div class="w-24 h-24 rounded-full border-2 border-dashed border-gray-300 flex items-center justify-center bg-gray-50 
                        text-2xl" aria-label="No pet photo">
                            🐾
                        </div>
                    </#if>
                    <div class="flex-grow">
                        <label for="imageUrl" class="block text-sm font-bold mb-1">Profile Image URL</label>
                        <p class="text-xs text-gray-500 mb-3">Use a web URL or one of PetPal's demo images.</p>
                        <input
                            id="imageUrl"
                            name="imageUrl"
                            type="text"
                            value="${pet.imageUrl!}"
                            list="pet-images"
                            placeholder="/content/dog.jpeg"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                        <datalist id="pet-images">
                            <option value="/content/dog.jpeg">Dog demo image</option>
                            <option value="/content/cat.jpg">Cat demo image</option>
                        </datalist>
                    </div>
                </div>

                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="name" class="block text-sm font-bold mb-2">Pet's Name <span class="text-red-500">*</span></label>
                        <input id="name" name="name" type="text" value="${pet.name!}" maxlength="80" required placeholder="e.g. Max" 
                        class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div>
                        <label for="speciesOrBreed" class="block text-sm font-bold mb-2">Species or Breed <span class="text-red-500">*</span></label>
                        <input id="speciesOrBreed" name="speciesOrBreed" type="text" value="${pet.speciesOrBreed!}" maxlength="120" required placeholder="e.g.
                         Golden Retriever" class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div>
                        <label for="age" class="block text-sm font-bold mb-2">Age in Years</label>
                        <input id="age" name="age" type="number" min="0" max="100" <#if pet.age??>value="${pet.age?c}"
                        </#if> placeholder="e.g. 4" class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div>
                        <label for="traits" class="block text-sm font-bold mb-2">Traits <span class="text-red-500">*</span></label>
                        <input id="traits" name="traits" type="text" value="${pet.traits!}" maxlength="200" required placeholder="
                        Friendly, high energy" class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                        <p class="text-xs text-gray-500 mt-2">Separate multiple traits with commas.</p>
                    </div>
                </div>

                <div>
                    <label for="specialCareInstructions" class="block text-sm font-bold mb-2">Special Care Instructions <span class="text-red-500">*</span></label>
                    <textarea id="specialCareInstructions" name="specialCareInstructions" rows="4" maxlength="1000" required placeholder="Allergies, 
                    medication, behavior, or feeding instructions" class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black resize-y">${pet.specialCareInstructions!}</textarea>
                </div>

                <div class="pt-6 border-t border-gray-100 flex justify-end gap-4">
                    <a href="/customer/${customerId?c}/dashboard#pets" class="px-6 py-2.5 rounded-lg font-semibold text-gray-600 hover:bg-gray-100 transition-colors">Cancel</a>
                    <button type="submit" class="bg-black text-white px-8 py-2.5 rounded-lg font-semibold hover:bg-gray-800 transition-colors shadow-sm">
                        Save Pet Profile
                    </button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
