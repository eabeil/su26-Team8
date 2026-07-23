<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard - PetPal</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex flex-col">
    <nav class="bg-white border-b border-gray-200 px-8 md:px-12 py-4 flex justify-between items-center sticky top-0 z-10">
        <a href="/customer/${customer.id?c}/dashboard" class="text-xl font-extrabold tracking-tight">PetPal</a>
        <div class="space-x-6 text-sm font-medium">
            <a href="/customer/${customer.id?c}/dashboard" class="text-black border-b-2 border-black pb-1">Dashboard</a>
            <a href="/customer/${customer.id?c}/providers" class="text-gray-500 hover:text-black transition-colors">Directory</a>
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
        <header class="mb-10">
            <h1 class="text-4xl font-bold mb-3">Welcome back, ${customer.name}!</h1>
            <p class="text-gray-500 text-lg">
                You have ${pets?size} pet<#if pets?size != 1>s</#if> and ${providers?size} local provider<#if providers?size != 1>s</#if> to explore.
            </p>
        </header>

        <#if successMessage??>
            <p class="bg-green-50 text-green-800 border border-green-200 rounded-xl px-5 py-3 mb-6 text-sm font-semibold" role="status">
                ${successMessage}
            </p>
        </#if>

        <section id="pets" class="mb-12 scroll-mt-24">
            <h2 class="text-2xl font-bold mb-6">My Pets</h2>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                <#list pets as pet>
                    <article class="bg-white border border-gray-200 rounded-xl p-6 shadow-sm flex flex-col">
                        <div class="flex justify-between items-start gap-3 mb-6">
                            <div class="flex items-center gap-4 min-w-0">
                                <#if pet.imageUrl?? && pet.imageUrl?has_content>
                                    <img src="${pet.imageUrl}" alt="${pet.name}" class="w-14 h-14 rounded-full object-cover border border-gray-200 flex-shrink-0">
                                <#else>
                                    <div class="w-14 h-14 rounded-full bg-gray-100 border border-gray-200 flex items-center justify-center text-xl flex-shrink-0" aria-label="No pet photo">
                                        🐾
                                    </div>
                                </#if>

                                <div class="min-w-0">
                                    <h3 class="text-lg font-bold truncate">${pet.name}</h3>
                                    <p class="text-sm text-gray-500">
                                        ${pet.speciesOrBreed}<#if pet.age??> · ${pet.age?c} yrs old</#if>
                                    </p>
                                </div>
                            </div>

                            <span class="bg-gray-100 text-xs font-bold px-2 py-1 rounded max-w-24 truncate flex-shrink-0" title="${pet.traits}">
                                ${pet.traits}
                            </span>
                        </div>

                        <a href="/customer/${customer.id?c}/pets/${pet.id?c}/edit" class="mt-auto w-full text-center text-sm font-semibold border border-gray-300 py-2 rounded hover:border-black transition-colors">
                            Edit Profile
                        </a>
                    </article>
                </#list>

                <a href="/customer/${customer.id?c}/pets/new" class="border-2 border-dashed border-gray-300 rounded-2xl p-5 flex flex-col items-center justify-center text-gray-500 hover:border-black hover:bg-gray-50 hover:text-black transition-colors min-h-[200px]">
                    <span class="text-3xl mb-2">+</span>
                    <span class="text-sm font-bold">Add a New Pet</span>
                </a>
            </div>
        </section>

        <section>
            <div class="flex justify-between items-end mb-6">
                <h2 class="text-xl font-bold">Local PetPals</h2>
                <a href="/customer/${customer.id?c}/providers" class="text-sm font-bold hover:underline">Find more PetPals &rarr;</a>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <#list providers as provider>
                    <#if provider?index lt 2>
                        <article class="bg-white border border-gray-200 rounded-xl p-6 shadow-sm flex justify-between items-center gap-4">
                            <div class="flex items-center gap-4 min-w-0">
                                <img src="${provider.imageUrl}" alt="${provider.name}" class="w-14 h-14 rounded-full object-cover border border-gray-200 flex-shrink-0">
                                <div class="min-w-0">
                                    <h3 class="text-lg font-bold truncate">${provider.name}</h3>
                                    <p class="text-sm text-gray-500 truncate">
                                        <#if provider.jobs?has_content>${provider.jobs?first.name}<#else>${provider.address}</#if>
                                    </p>
                                    <p class="text-sm mt-1">
                                        <span class="text-gray-500">${provider.reviews?size} review<#if provider.reviews?size != 1>s</#if></span>
                                    </p>
                                </div>
                            </div>

                            <a href="/customer/${customer.id?c}/providers/${provider.id?c}" class="text-sm font-semibold border-2 border-black px-4 py-2 rounded hover:bg-black hover:text-white transition-colors flex-shrink-0">
                                View
                            </a>
                        </article>
                    </#if>
                <#else>
                    <p class="text-sm text-gray-500">No providers are available yet.</p>
                </#list>
            </div>
        </section>
    </main>
</body>
</html>
