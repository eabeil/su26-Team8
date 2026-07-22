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
            <a href="/customer/${customerId?c}/providers" class="text-black border-b-2 border-black pb-1">Directory</a>
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
    
    <main class="max-w-5xl mx-auto px-8 md:px-12 py-10 flex-grow w-full">
        <a href="/customer/${customerId?c}/providers" class="text-sm font-semibold text-gray-500 hover:text-black mb-6 inline-block">&larr; Back to Directory</a>

        <#if successMessage??>
            <p class="bg-green-50 text-green-800 border border-green-200 rounded-xl px-5 py-3 mb-6 text-sm font-semibold" role="status">
                ${successMessage}
            </p>
        </#if>
        
        <div class="grid grid-cols-1 lg:grid-cols-5 gap-12">
            
            <!--coloumn-->
            <div class="lg:col-span-3 space-y-8">
                
                <!--grid-->
                <section class="bg-white rounded-2xl border border-gray-200 shadow-sm overflow-hidden">
                    
                    <!--hop half-->
                    <div class="p-8 flex flex-col sm:flex-row items-start gap-6 border-b border-gray-100">
                        <img src="${provider.imageUrl}" alt="${provider.name}" class="w-32 h-32 rounded-full object-cover border border-gray-200 flex-shrink-0">
                        <div>
                            <h1 class="text-3xl font-extrabold mb-1">${provider.name}</h1>
                            <p class="text-lg text-gray-500 mb-4">${provider.address}</p>
                            <div class="flex flex-wrap items-center gap-3">
                                <span class="font-semibold text-sm">${reviews?size} customer review<#if reviews?size != 1>s</#if></span>
                                <span class="text-gray-300">|</span>
                                <span class="bg-green-50 text-green-700 text-xs font-bold px-3 py-1 rounded-full border border-green-200">✓ Background Checked</span>
                            </div>
                        </div>
                    </div>

                    <!--info-->
                    <div class="p-8 bg-gray-50/50">
                        <h2 class="text-xl font-bold mb-3">About ${provider.name}</h2>
                        <div class="text-gray-600 space-y-4 leading-relaxed text-sm">
                            <p>${provider.description}</p>
                        </div>
                    </div>
                </section>

                <!--service info-->
                <section>
                    <h2 class="text-xl font-bold mb-4">Services & Rates</h2>
                    <div class="bg-white border border-gray-200 rounded-2xl overflow-hidden shadow-sm">
                        <#list jobs as job>
                            <div class="flex justify-between items-center gap-4 p-6 border-b border-gray-100 last:border-b-0">
                                <div>
                                    <p class="font-semibold">${job.name}</p>
                                    <p class="text-sm text-gray-500 mt-1">${job.duration}</p>
                                </div>
                                <span class="text-gray-700 font-semibold">$${job.price?string["0.00"]}</span>
                            </div>
                        <#else>
                            <p class="p-6 text-sm text-gray-500">Contact this provider for current services and rates.</p>
                        </#list>
                    </div>
                </section>
                <!--reviews-->

                <section id="reviews">
                    <h2 class="text-2xl font-bold mb-4">Reviews (${reviews?size})</h2>
                    <div class="space-y-4">
                        <#list reviews as review>
                            <article class="bg-white border border-gray-200 rounded-xl p-5 shadow-sm">
                                <div class="flex items-center justify-between gap-4 mb-2">
                                    <span class="font-bold">${review.customer.name}</span>
                                    <#if review.wasEdited>
                                        <span class="text-xs text-gray-500">Edited</span>
                                    </#if>
                                </div>

                                <p class="text-xs font-bold mb-2 uppercase tracking-wider">
                                    <#if review.recommended>
                                        👍 Recommends
                                    <#else>
                                        👎 Does not recommend
                                    </#if>
                                </p>
                                <p class="text-gray-600 text-sm leading-relaxed">${review.customerComment}</p>
                            </article>

                        <#else>
                            <div class="bg-white border-2 border-dashed border-gray-300 rounded-xl p-8 text-center">
                                <p class="font-semibold">No reviews yet.</p>
                                <p class="text-sm text-gray-500 mt-1">Be the first customer to share an experience.</p>
                            </div>
                        </#list>
                    </div>
                </section>
            </div>

            <!--right coloumn-->
            <aside class="lg:col-span-2 space-y-8">
                <section class="bg-black text-white rounded-2xl p-6 shadow-md">
                    <#if jobs?has_content>
                        <h2 class="text-3xl font-extrabold mb-1">$${jobs?first.price?string["0.00"]}
                            <span class="text-base text-gray-400 font-normal"> / hour starting rate</span>
                        </h2>
                    <#else>
                        <h2 class="text-xl font-bold mb-1">Contact for rates</h2>
                    </#if>
                    <p class="text-sm text-gray-400 mb-6">Scheduling happens directly with the provider.</p>

                            
                    <!--contact info-->
                    <div class="space-y-4 mb-8 text-sm">
                        <div>
                            <p class="text-xs font-bold text-gray-400 uppercase tracking-wider mb-1">Phone Number</p>
                            <p class="font-medium">${provider.phone}</p>
                        </div>
                        <div>
                            <p class="text-xs font-bold text-gray-400 uppercase tracking-wider mb-1">Email Address</p>
                            <p class="font-medium">${provider.email}</p>
                        </div>
                    </div>

                    <div class="space-y-3">
                        <a href="#" class="flex justify-center w-full px-4 py-3 text-black bg-white rounded-lg hover:bg-gray-200 transition-colors font-semibold">
                        Call Provider
                        </a>
                        <a href="#" class="flex justify-center w-full px-4 py-3 text-white bg-black border border-gray-600 rounded-lg hover:bg-gray-800 transition-colors font-semibold">
                        Email Provider
                        </a>
                    </div>
                    
                    <button class="flex items-center justify-center gap-2 w-full mt-6 text-sm font-bold text-gray-400 hover:text-white transition-colors">
                        <span class="text-lg">♡</span> Save to Favorites
                    </button>
                </section>
                
                <section class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm">
                    <h2 class="font-bold mb-3">Leave a Review</h2>
                    <form
                        method="post"
                        action="/customer/${customerId?c}/providers/${provider.id?c}/reviews">
                        <fieldset>
                            <legend class="text-sm font-semibold mb-3">Would you recommend this provider?</legend>
                            <div class="grid grid-cols-2 gap-3 mb-4">
                                <label class="cursor-pointer">
                                    <input type="radio" name="recommended" value="true" required class="peer sr-only">
                                    <span class="flex items-center justify-center gap-2 px-3 py-2.5 border border-gray-300 rounded-lg peer-checked:bg-black peer-checked:text-white peer-checked:border-black transition-colors text-sm font-semibold">
                                        👍 Recommend
                                    </span>
                                </label>
                                <label class="cursor-pointer">
                                    <input type="radio" name="recommended" value="false" required class="peer sr-only">
                                    <span class="flex items-center justify-center gap-2 px-3 py-2.5 border border-gray-300 rounded-lg peer-checked:bg-black peer-checked:text-white peer-checked:border-black transition-colors text-sm font-semibold">
                                        👎 Don't
                                    </span>
                                </label>
                            </div>
                        </fieldset>

                        <label for="customerComment" class="block text-sm font-semibold mb-2">Written Review</label>
                        <textarea
                            id="customerComment"
                            name="customerComment"
                            placeholder="Share your experience working with ${provider.name}..."
                            minlength="3"
                            maxlength="1500"
                            required
                            class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black mb-3"
                            rows="4"></textarea>

                        <button
                            type="submit"
                            class="bg-black text-white w-full py-2.5 rounded-lg font-semibold hover:bg-gray-800 transition-colors">
                            Post Review
                        </button>
                    </form>
                </section>
            </aside>
        </div>
    </main>
</body>
</html>


