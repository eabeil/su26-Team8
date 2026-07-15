<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Directory - PetPal</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex flex-col">
    <!--navigation-->
    <nav class="bg-white border-b border-gray-200 px-8 md:px-12 py-4 flex justify-between items-center sticky top-0 z-10">
        <a href="/customer/${customerId?c}/dashboard" class="text-xl font-extrabold tracking-tight">PetPal</a>
        <div class="space-x-6 text-sm font-medium">
            <a href="/customer/${customerId?c}/dashboard" class="text-gray-500 hover:text-black transition-colors">Dashboard</a>
            <a href="/customer/${customerId?c}/providers"" class="text-black border-b-2 border-black pb-1">Directory</a>
            <a href="profile.html" class="text-gray-500 hover:text-black transition-colors">My Profile</a>
            <a href="../common/index.html" class="text-red-600 hover:text-red-800 transition-colors ml-4">Log Out</a>
        </div>
    </nav>

    <main class="max-w-6xl mx-auto px-8 md:px-12 py-10 flex-grow w-full flex flex-col md:flex-row gap-8">
        
        <!--info bar-->
        <aside class="w-full md:w-1/3 lg:w-1/4">
            <div class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm sticky top-24">
                <h2 class="text-lg font-bold mb-5">Filter Search</h2>
                
                <form onsubmit="event.preventDefault();" class="space-y-5">
                    <div>
                        <label class="block text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">Keyword</label>
                        <div class="relative">
                            <input type="text" placeholder="Search by name..." class="w-full pl-10 pr-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black text-sm transition-shadow">
                            <svg class="w-4 h-4 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                            </svg>
                        </div>
                    </div>

                    <!--location-->
                    <div>
                        <label class="block text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">Location</label>
                        <input type="text" value="Greensboro, NC" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black text-sm">
                    </div>

                    <!--service type-->
                    <div>
                        <label class="block text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">Service Type</label>
                        <select class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black text-sm bg-white">
                            <option>All Services</option>
                            <option>Dog Walking</option>
                            <option>Pet Sitting</option>
                            <option>Grooming</option>
                            <option>Boarding</option>
                        </select>
                    </div>

                    <!--price raneg-->
                    <div>
                        <label class="block text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">Max Hourly Rate</label>
                        <input type="range" min="15" max="100" value="50" class="w-full accent-black cursor-pointer">
                        <div class="flex justify-between text-xs text-gray-500 mt-1">
                            <span>$15</span>
                            <span>$50 selected</span>
                            <span>$100+</span>
                        </div>
                    </div>

                    <!--filter-->
                    <div class="pt-4 border-t border-gray-100">
                        <button type="submit" class="w-full bg-black text-white px-4 py-2.5 rounded-lg font-semibold hover:bg-gray-800 transition-colors text-sm">
                            Apply Filters
                        </button>
                    </div>
                </form>
            </div>
        </aside>

        <!--right side search-->
        <div class="w-full md:w-2/3 lg:w-3/4">
            
            <div class="flex justify-between items-end mb-6">
                <div>
                    <h1 class="text-2xl font-bold">Top PetPals near Greensboro</h1>
                    <p class="text-sm text-gray-500 mt-1">Showing 2 highly rated matches</p>
                </div>
                <!--sorting:down-->
                <select class="px-4 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-black bg-white">
                    <option>Sort by: Recommended</option>
                    <option>Highest Rated</option>
                    <option>Price: Low to High</option>
                </select>
            </div>

            <!--cards-->
            <div class="space-y-6">
            <#list providers as pal>
                <!--card one-->
                <div class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm flex flex-col sm:flex-row gap-6 hover:shadow-md transition-shadow">
                    <!-- Photo -->
                    <img src="${pal.imageUrl}" alt="${pal.name}" class="w-24 h-24 sm:w-32 sm:h-32 rounded-xl object-cover border border-gray-200 flex-shrink-0">
                    
                    <!-- Content -->
                    <div class="flex-grow flex flex-col">
                        <div class="flex justify-between items-start">
                            <div>
                                <h3 class="text-xl font-bold">${pal.name}</h3>
                                <p class="text-sm text-gray-500">${pal.address}</p>
                            </div>
                            <#if provider.jobs?has_content>
                            <p class="font-bold text-lg whitespace-nowrap">
                                $${provider.jobs?first.price?string["0.00"]}
                                <span class="text-sm text-gray-500 font-normal">starting</span>
                            </p>
                            </#if>
                        </div>

                        <!--ratings-->
                        <div class="flex flex-wrap items-center gap-3 mt-2 mb-3">
                            <span class="font-semibold text-sm">${provider.reviews?size} review<#if provider.reviews?size != 1>s</#if></span>
                            <span class="text-gray-300">|</span>
                            <span class="text-xs font-semibold px-2 py-1 bg-green-50 text-green-700 border border-green-200 rounded text-center">Background Checked</span>
                        </div>

                        <!--bio-->
                        <p class="text-sm text-gray-600 line-clamp-2 mb-4">
                            ${pal.description}
                        </p>

                        <!--info-->
                        <div class="mt-auto flex justify-end">
                            <a href="/customer/${customerId?c}/providers/${provider.id?c}" class="text-sm font-semibold border-2 border-black px-6 py-2 rounded-lg hover:bg-black hover:text-white transition-colors">View Profile</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

</body>
</html>